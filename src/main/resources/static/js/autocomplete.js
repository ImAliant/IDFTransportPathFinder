let selectedStation = null;

async function suggestStations() {
    const input = document.getElementById("station-input");
    const query = input.value.trim();
    const suggestionsDiv = document.getElementById("suggestions");

    if (!query) {
        suggestionsDiv.innerHTML = "";
        selectedStation = null;
        return;
    }

    const res = await fetch(`/api/stations/search?name=${encodeURIComponent(query)}`);
    const stations = await res.json();

    suggestionsDiv.innerHTML = "";
    selectedStation = null;

    stations.forEach(station => {
        const div = document.createElement("div");
        div.textContent = `${station.name} (${station.lineKey.routeType} - ${station.lineKey.name})`;
        div.classList.add("suggestion-item");
        div.onclick = () => {
            input.value = station.name;
            suggestionsDiv.innerHTML = "";
            selectedStation = station;
        };
        suggestionsDiv.appendChild(div);
    });
}

function searchStation() {
    if (!selectedStation) {
        alert("Please select a station from the suggestions.");
        return;
    }
    focusMapOnStation(selectedStation);
}

function focusMapOnStation(station) {
  map.setView([station.latitude, station.longitude], 18); 
  L.marker([station.latitude, station.longitude]).addTo(map)
    .bindPopup(station.name)
    .openPopup();
}