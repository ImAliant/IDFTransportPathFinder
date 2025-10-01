let selectedStart = null;
let selectedEnd = null;
let currentRoute = null;

document.addEventListener('DOMContentLoaded', () => {
    const startInput = document.getElementById('start-input');
    const endInput = document.getElementById('end-input');
    const startSuggestions = document.getElementById('start-suggestions');
    const endSuggestions = document.getElementById('end-suggestions');

    startInput.addEventListener('input', () => handleSuggest(startInput, startSuggestions, (station) => onStationSelected(station, true)));
    endInput.addEventListener('input', () => handleSuggest(endInput, endSuggestions, (station) => onStationSelected(station, false)));
});

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('start-btn').addEventListener('click', startSearch);
    document.getElementById('route-btn').addEventListener('click', routeSearch);
});

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('reset-btn').addEventListener('click', resetRoute);
})

function onStationSelected(station, isStart) {
    if (isStart) {
        selectedStart = station;
        document.getElementById('start-input').value = station ? station.name : '';
        document.getElementById('start-suggestions').innerHTML = '';
    } else {
        selectedEnd = station;
        document.getElementById('end-input').value = station ? station.name : '';
        document.getElementById('end-suggestions').innerHTML = '';
    }

    if (station) {
        const latLng = L.latLng(station.latitude, station.longitude);
        map.setView(latLng, map.getZoom()); // Adjust zoom level as needed
    }
}


async function handleSuggest(inputElem, suggestionBox, onSelect) {
    const query = inputElem.value.trim();
    suggestionBox.innerHTML = '';
    if (!query) {
        onSelect(null);
        return;
    }

    try {
        const res = await fetch(`/api/stations/search?name=${encodeURIComponent(query)}`);
        const stations = await res.json();

        stations.forEach(station => {
            const div = document.createElement('div');
            div.className = 'suggestion-item';
            div.textContent = `${station.name} (${station.lineKey.routeType} - ${station.lineKey.name})`;
            div.addEventListener('click', () => {
                inputElem.value = station.name;
                suggestionBox.innerHTML = '';
                onSelect(station);
            });
            suggestionBox.appendChild(div);
        });
    } catch (error) {
        console.error('Error fetching station suggestions:', error);
    }
}

function startSearch() {
    if (!selectedStart) {
        alert("Veuillez sélectionner une station de départ depuis les suggestions.");
        return;
    }
    document.getElementById('destination-section').style.display = 'block';
    document.getElementById('reset-btn').style.display = 'inline-block';
}

function routeSearch() {
    if (!selectedStart || !selectedEnd) {
        alert("Veuillez sélectionner les stations de départ et d'arrivée.");
        return;
    }

    if (currentRoute) {
        map.removeControl(currentRoute);
    }

    currentRoute = L.Routing.control({
        waypoints: [
            L.latLng(selectedStart.latitude, selectedStart.longitude),
            L.latLng(selectedEnd.latitude, selectedEnd.longitude)
        ],
        routeWhileDragging: true
    }).addTo(map);
}

function resetRoute() {
    if (currentRoute) {
        map.removeControl(currentRoute);
        currentRoute = null;
    }
    selectedStart = null;
    selectedEnd = null;
    document.getElementById('start-input').value = '';
    document.getElementById('end-input').value = '';
    document.getElementById('start-suggestions').innerHTML = '';
    document.getElementById('end-suggestions').innerHTML = '';
    document.getElementById('destination-section').style.display = 'none';
    document.getElementById('reset-btn').style.display = 'none';
}