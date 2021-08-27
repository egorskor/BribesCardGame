ReactDOM.render(
    <button onClick={makeTurn}>
        Make turn
    </button>,
    document.getElementById('main-player')
);

async function makeTurn() {
    let request = {
        suit : "CROSS",
        number: 1
    }
    let response = await fetch('/makeTurn?playerId=0',
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(request)
        });
    let text = await response.text();
    alert(text);
}