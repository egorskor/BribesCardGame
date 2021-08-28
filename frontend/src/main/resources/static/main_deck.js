ReactDOM.render(
    <button onClick={initGame}>
        Init game
    </button>,
    document.getElementById('init-game')
);

async function initGame() {
    let response = await fetch('/initGame');
    let text = await response.text(); // прочитать тело ответа как текст
    alert(text);
}

ReactDOM.render(
    <div>
        Init game
    </div>,
    document.getElementById('game-state')
);

let socket = new WebSocket("wss://localhost:8080/getGameState");

// получение сообщения - отобразить данные в div#messages
socket.onmessage = function(event) {
    let message = event.data;

    ReactDOM.render(
        <div>
            {message}
        </div>,
        document.getElementById('game-state')
    );
}
