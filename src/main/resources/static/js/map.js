let latitude = 48.857;
let longitude = 2.3996;
let zoom = 20;
let map = L.map('map').setView([latitude, longitude], zoom);

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    	attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
	}).addTo(map);

let loadTimeout;
function debouncedLoadStations() {
	clearTimeout(loadTimeout);
	loadTimeout = setTimeout(loadStations, 50);
}

function loadStations() {
    const bounds = map.getBounds();
	const zoom = map.getZoom();

	const params = new URLSearchParams({
		south: bounds.getSouth(),
    	north: bounds.getNorth(),
    	west: bounds.getWest(),
    	east: bounds.getEast(),
    	zoom: zoom
  	});

	fetch(`/api/stations/viewport?${params}`)
		.then(res => res.json())
		.then(data => {
			map.eachLayer(layer => {
				if (layer instanceof L.Marker && !layer._tileCoords) {
					map.removeLayer(layer);
				}
			});

			data.forEach(station => {
				if (zoom > station.zoomThreshold) {
					const icon = L.icon({
            			iconUrl: getIconForType(station.lineKey.routeType),
            			iconSize: [20, 20]
          			});

					L.marker([station.latitude, station.longitude], { icon: icon })
						.addTo(map)
						.bindPopup(`
							<strong>${station.name}</strong><br>
							${station.lineKey?.name ?? 'N/A'} (${station.lineKey?.routeType ?? 'N/A'})`
						)
				}
			});
		});
}

function getIconForType(type) {
	if (!type) {
		return '/stop-icon/default-icon.png';
	}

	let iconUrl;
	switch (type) {
		case 'RER':
			iconUrl = '/stop-icon/rer-icon.png';
			break;
		case 'TER':
			iconUrl = '/stop-icon/ter-icon.png';
			break;
        case 'Bus':
			iconUrl = '/stop-icon/bus-icon.png';
			break;
        case 'Tram':
			iconUrl = '/stop-icon/tram-icon.png';
			break;
        case 'Subway':
			iconUrl = '/stop-icon/metro-icon.png';
			break;
        case 'Funicular':
			iconUrl = '/stop-icon/funicular-icon.png';
			break;
		default:
			iconUrl = '/stop-icon/default-icon.png';
			break;
	}
	return iconUrl;
}

map.on('load', loadStations);
map.on('moveend', debouncedLoadStations);
map.on('zoomend', debouncedLoadStations);