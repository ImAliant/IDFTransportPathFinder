let buttonSelected = null;

let selectedLine = null;
let selectedDiv = null;

const lineButtons = document.querySelectorAll('.line-button');

lineButtons.forEach(button => {
    button.addEventListener('click', function(event) {
        event.preventDefault();
        const type = this.getAttribute('data-type');
        console.log(`Line type clicked: ${type}`);
        
        handleButtonClick(this, type);
    });
});

function handleButtonClick(clickedButton, type) {
    const isCurrentlySelected = isButtonSelected(clickedButton);
    
    if (isCurrentlySelected) {
        // Désélectionner le bouton actuel
        deselectButton();
        clearScroller();
        return;
    }
    
    // Sélectionner le nouveau bouton (désélectionne automatiquement l'ancien s'il existe)
    if (hasSelectedButton()) {
        deselectButton();
    }
    
    selectButton(clickedButton);
    updateScroller(type);
}

async function updateScroller(type) {
    const lineScroller = document.getElementById('line-scroller');
    lineScroller.innerHTML = '';

    if (!type) {
        return;
    }

    lineScroller.style.display = 'block';

    try {
        const response = await fetch(`/api/lines/routeType?type=${encodeURIComponent(type)}`);
        const lines = await response.json();

        if (lines.length === 0) {
            displayMessage(lineScroller, 'No lines available for this type.');
            return;
        }

        renderLines(lineScroller, lines);
    } catch (error) {
        console.error('Error fetching lines:', error);
        displayMessage(lineScroller, 'Error loading lines. Please try again later.');
    }
}

function sortLines(lines) {
    return lines.sort((a, b) => {
        const aNum = parseInt(a.name, 10);
        const bNum = parseInt(b.name, 10);
        
        // Si les deux sont des nombres, tri numérique
        if (!isNaN(aNum) && !isNaN(bNum)) {
            return aNum - bNum;
        }
        
        // Si un seul est un nombre, les nombres viennent en premier
        if (!isNaN(aNum) && isNaN(bNum)) {
            return -1;
        }
        if (isNaN(aNum) && !isNaN(bNum)) {
            return 1;
        }
        
        // Si aucun n'est un nombre, tri alphabétique
        return a.name.localeCompare(b.name);
    });
}

function createLineElement(line) {
    const lineDiv = document.createElement('div');
    lineDiv.className = 'line-item';
    
    lineDiv.textContent = `${line.name} (${line.routeType})`;
    lineDiv.addEventListener('click', () => onLineSelected(line, lineDiv));
    
    return lineDiv;
}

async function onLineSelected(line, lineItem) {
    const isCurrentlySelected = selectedDiv === lineItem;
    
    if (isCurrentlySelected) {
        // Désélectionner la ligne actuelle
        deselectLine();
        console.log('Deselected line:', line);
        return;
    }
    
    // Désélectionner l'ancienne ligne s'il y en a une
    if (selectedDiv) {
        deselectLine();
    }
    
    // Sélectionner la nouvelle ligne
    selectLine(line, lineItem);
    console.log('Selected line:', selectedLine);
}

function selectLine(line, lineItem) {
    selectedLine = line;
    selectedDiv = lineItem;
    selectedDiv.classList.add('enabled');
}

function deselectLine() {
    if (selectedDiv) {
        selectedDiv.classList.remove('enabled');
    }
    selectedLine = null;
    selectedDiv = null;
}

function displayMessage(container, message) {
    container.innerHTML = `<p>${message}</p>`;
}

function renderLines(container, lines) {
    const sortedLines = sortLines([...lines]);
    sortedLines.forEach(line => {
        container.appendChild(createLineElement(line));
    });
}

function hasSelectedButton() {
    return buttonSelected !== null;
}

function isButtonSelected(button) {
    return buttonSelected === button;
}

function deselectButton() {
    if (buttonSelected) {
        buttonSelected.classList.remove('enabled');
        buttonSelected = null;
    }
}

function selectButton(button) {
    button.classList.add('enabled');
    buttonSelected = button;
}

function clearScroller() {
    const lineScroller = document.getElementById('line-scroller');
    lineScroller.innerHTML = '';
    lineScroller.style.display = 'none';
}