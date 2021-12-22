<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cars</title>
</head>
<body>
<script>
    function addCar(model, color) {
        let request = new XMLHttpRequest();
        let body = {
            "model" : model,
            "color" : color
        };

        request.open('POST', '/cars', false);
        request.setRequestHeader('Content-Type', 'application/json');

        request.send(
            JSON.stringify(body)
        );

        if (request.status !== 200) {
            alert('Error!');
        } else {
            let html =
                '<tr>' +
                '<th>Id</th>' +
                '<th>Model</th>' +
                '<th>Color</th>' +
                '</tr>'

            let response = JSON.parse(request.response);

            for (let i = 0; i < response.length; i++) {
                html += '<tr>';
                html += '<td>' + response[i]['id'] + '</td>';
                html += '<td>' + response[i]['model'] + '</td>';
                html += '<td>' + response[i]['color'] + '</td>';
                html += '</tr>';
            }

            document.getElementById('cars_table').innerHTML = html;
        }
    }
</script>
<h1>Cars Page</h1>
<div>
    <input id="modelInput" placeholder="Model"><br>
    <input id="colorInput" placeholder="Color"><br>
    <button onclick="addCar(document.getElementById('modelInput').value, document.getElementById('colorInput').value)">Add</button>
</div>
<br>
<table id="cars_table" border="1">
    <tr>
        <th>Id</th>
        <th>Model</th>
        <th>Color</th>
    </tr>
    <#list cars as car>
        <tr>
            <td>${car.id}</td>
            <td>${car.model}</td>
            <td>${car.color}</td>
        </tr>
    </#list>
</table>
</body>
</html>
