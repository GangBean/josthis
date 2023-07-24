// REST API 호출 및 JSON 데이터를 HTML 테이블로 변환하는 함수
function createTableFromJSON(data) {
    const tableBody = document.querySelector('#stockTable tbody');

    const stockArray = data.stocks;

    // JSON 데이터의 각 요소를 테이블 행으로 변환하여 추가합니다.
    stockArray.forEach(function (item) {
        const row = document.createElement('tr');
        const idCell = document.createElement('td');
        const nameCell = document.createElement('td');
        const themeCell = document.createElement('td');
        const analystCell = document.createElement('td');
        const consensusCell = document.createElement('td');
        const infoCell = document.createElement('td');
        const josthisCell = document.createElement('td');

        idCell.textContent = item.id;
        idCell.hidden = true
        nameCell.textContent = item.name + ' (' + item.tickerCode + ')';
        themeCell.textContent = '';
        analystCell.textContent = '';
        consensusCell.textContent = item.consensusScore.toFixed(1) + ' / ' + item.consensusType;
        infoCell.textContent = item.price;
        josthisCell.textContent = '';

        row.appendChild(idCell);
        row.appendChild(nameCell);
        row.appendChild(themeCell);
        row.appendChild(analystCell);
        row.appendChild(consensusCell);
        row.appendChild(infoCell);
        row.appendChild(josthisCell);

        tableBody.appendChild(row);
    });
}

// REST API 호출 후 JSON 데이터를 받아옵니다.
fetch('http://localhost:8080/api/stocks') // 실제 REST API의 엔드포인트를 사용해야 합니다.
    .then(response => response.json())
    .then(data => createTableFromJSON(data))
    .catch(error => console.error('Error fetching data:', error));
