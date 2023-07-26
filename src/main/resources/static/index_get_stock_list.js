// REST API 호출 및 JSON 데이터를 HTML 테이블로 변환하는 함수
const GET_STOCK_API_URL = window.location.origin + '/api/stocks'

function createTableFromJSON(data) {
    const tableBody = document.querySelector('#stockTable tbody');
    const stockArray = data.stocks;

    // JSON 데이터의 각 요소를 테이블 행으로 변환하여 추가합니다.
    stockArray.forEach(item => addNewRowToTable(item, tableBody));
}

function addNewRowToTable(item, tableBody) {
    const row = document.createElement('tr');
    const idCell = document.createElement('td');
    const nameCell = document.createElement('td');
    const themeCell = document.createElement('td');
    const analystCell = document.createElement('td');
    const consensusCell = document.createElement('td');
    const infoCell = document.createElement('td');
    const josthisCell = document.createElement('td');

    idCell.textContent = item.id;
    idCell.hidden = true;
    idCell.id = 'stockId';
    nameCell.textContent = item.name + ' (' + item.tickerCode + ')';
    nameCell.id = 'stockName';
    themeCell.textContent = '';
    themeCell.id = 'stockTheme';
    analystCell.textContent = '';
    analystCell.id = 'analyst'
    consensusCell.textContent = item.consensusScore.toFixed(1) + ' / ' + item.consensusType;
    consensusCell.id = 'consensus';
    infoCell.textContent = item.price;
    infoCell.id = 'stockInfo';
    josthisCell.textContent = '';
    josthisCell.id = 'josthis'

    row.appendChild(idCell);
    row.appendChild(nameCell);
    row.appendChild(themeCell);
    row.appendChild(analystCell);
    row.appendChild(consensusCell);
    row.appendChild(infoCell);
    row.appendChild(josthisCell);

    tableBody.appendChild(row);
}

function fetchData(url) {
    return fetch(url)
        .then(response => response.json())
        .then(data => createTableFromJSON(data))
        .catch(error => console.error('Error fetching data:', error));
}

// API를 호출하여 데이터를 가져오는 함수
function fetchMoreData() {
    let MORE_STOCK_API_URL = GET_STOCK_API_URL;
    const table = document.querySelector('#stockTable tbody');
    const rows = table.getElementsByTagName('tr');

    if (rows != null) {
        const lastRow = rows[rows.length - 1];
        const stockId= lastRow.querySelector('#stockId').textContent;
        const consensusScore = lastRow.querySelector('#consensus').textContent.substring(0, 3);
        MORE_STOCK_API_URL = MORE_STOCK_API_URL + '?lastScore=' + consensusScore + '&lastId=' + stockId;
    }

    return new Promise((resolve, reject) => {
        setTimeout(() => {
            fetchData(MORE_STOCK_API_URL)
                .then(data => {
                    const tbody = document.querySelector('#stockTable tbody');
                    createTableFromJSON(data);
                    resolve();
                })
                .catch(error => {
                    console.error('Failed to fetch more data:', error);
                    reject()
                });
        }, 1000); // 1초 후 데이터를 반환 (실제로는 서버에서 비동기로 데이터를 가져와야 함)
    });
}

// 무한 스크롤 이벤트 핸들러
function handleScroll() {
    const table = document.getElementById('stockTable');
    const tableBottom = table.getBoundingClientRect().bottom;
    const windowBottom = window.innerHeight;

    if (tableBottom <= windowBottom) {
        // 테이블의 하단이 화면의 하단에 도달하면 데이터를 추가로 로드
        fetchMoreData()
            .then(data => {
                // 가져온 데이터를 테이블에 동적으로 추가
                const tbody = table.querySelector('tbody');
                data.forEach(item => addNewRowToTable(item, tbody));
            })
            .catch(error => {
                console.error('Failed to fetch more data:', error);
            });
    }
}

// 스크롤 이벤트 리스너 등록
window.addEventListener('scroll', handleScroll);

// 페이지 로드시 REST API 호출 후 JSON 데이터를 받아옵니다.
fetchData(GET_STOCK_API_URL);