<html>
<head>
    <title>Search Users</title>
</head>
<body>
<script>
    function searchUsers(email) {
        let request = new XMLHttpRequest();

        request.open('GET', '/search/users/byEmail?email=' + email, false);

        request.send();

        if (request.status !== 200) {
            alert('Ошибка!');
        } else {
            let html =
                '<tr>' +
                '<th>ID</th>' +
                '<th>First Name</th>' +
                '<th>Last Name</th>' +
                '<th>Email</th>' +
                '</tr>'

            let response = JSON.parse(request.response);

            for (let i = 0; i < response.length; i++) {
                html += '<tr>';
                html += '<td>' + response[i]['id'] + '</td>';
                html += '<td>' + response[i]['firstName'] + '</td>';
                html += '<td>' + response[i]['lastName'] + '</td>';
                html += '<td>' + response[i]['email'] + '</td>';
                html += '</tr>';
            }

            document.getElementById('accounts_table').innerHTML = html;
        }
    }
</script>
<h1>Search page</h1>
<label>
    <input id="email" name="email" placeholder="Email" onkeyup="searchUsers(document.getElementById('email').value)">
    Search by Email
</label>
<br>
<table id="accounts_table"></table>
</body>
</html>
