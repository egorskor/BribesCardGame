ReactDOM.render(
    <button onClick={initGame}>
        Init game
    </button>,
    document.getElementById('main-deck')
);

async function initGame() {

    let response = await fetch('/initGame');
    let text = await response.text(); // прочитать тело ответа как текст
    alert(text);
}
