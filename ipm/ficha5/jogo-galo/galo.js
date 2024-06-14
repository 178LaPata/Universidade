const grid = document.querySelector('.grid'); 
const cells = document.querySelectorAll('.cell'); 
const resetButton = document.querySelector('#reset'); 
let currentPlayer = 'X';
let gameActive = true;
let gameState = ['', '', '', '', '', '', '', '', ''];

function checkWinner() {
    const winningCombinations = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],
        [0, 3, 6], [1, 4, 7], [2, 5, 8],
        [0, 4, 8], [2, 4, 6]             
    ];
    for (let i = 0; i <= 7; i++) {
        const winCondition = winningCombinations[i];
        const a = gameState[winCondition[0]];
        const b = gameState[winCondition[1]];
        const c = gameState[winCondition[2]];
        if (a === '' || b === '' || c === '') {
        continue;
        }
        if (a === b && b === c) {
        gameActive = false;
        return a;
        }
    }
    if (!gameState.includes('')) {
        gameActive = false;
        return 'Empate';
    }
    return null;
}

function handleCellPlayed(clickedCell, clickedIndex) {
    if (gameState[clickedIndex] !== '' || !gameActive) {
        if (!gameActive) {
        alert('Jogo Terminado! Faça reset para recomeçar.');
        } else {
        alert('Célula já preenchida');
        }
        return;
    }
    gameState[clickedIndex] = currentPlayer;
    clickedCell.textContent = currentPlayer;
}

function handlePlayerChange() {
    currentPlayer = (currentPlayer === 'X') ? 'O' : 'X';
}

function handleCellClick(event) {
    const clickedCell = event.target;
    const clickedIndex = parseInt(clickedCell.getAttribute('data-cell-index')); 
    handleCellPlayed(clickedCell, clickedIndex);
    const winner = checkWinner();
    if (winner) {
        if (winner === 'Empate') {
        alert('Empate!');
        } else {
        alert('Jogador ' + winner + ' venceu!');
        }
    } else {
        handlePlayerChange();
    }
}

function handleRestartGame() {
    gameActive = true;
    currentPlayer = 'X';
    gameState = ['', '', '', '', '', '', '', '', ''];
    cells.forEach(cell => cell.textContent = '');
}

cells.forEach((cell, index) => {
    cell.addEventListener('click', handleCellClick);
});

resetButton.addEventListener('click', handleRestartGame);
