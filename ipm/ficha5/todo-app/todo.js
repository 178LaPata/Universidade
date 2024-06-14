const todos = [];

function addCurrentDate() {
    const dateElement = document.getElementById('list-date');
    const now = new Date();
    dateElement.textContent = now.toLocaleDateString('pt-PT'); 
}

function taskExists(task) {
    return todos.includes(task.trim());
}

function renderTodos() {
    const list = document.getElementById('todo-list');
    list.innerHTML = ''; 
    todos.forEach((task, index) => {
        const listItem = document.createElement('li');
        listItem.className = 'todo-list-item';
        const text = document.createElement('p');
        text.textContent = task;
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', function() {
            todos.splice(index, 1); 
            renderTodos(); 
        });

    listItem.appendChild(text);
    listItem.appendChild(deleteButton);
    list.appendChild(listItem);
    });
}

document.addEventListener('DOMContentLoaded', function() {
    addCurrentDate();
    const form = document.getElementById('todo-form');
    form.addEventListener('submit', function(event) {
    event.preventDefault();
    const input = event.target.querySelector('input[type="text"]');
    const task = input.value.trim();

    if (task && !taskExists(task)) {
        todos.push(task);
        renderTodos();
    } else {
        alert('Please enter a non-empty unique task.');
    }
    input.value = ''; 
    });
});

renderTodos(); 
