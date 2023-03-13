<html>
<head>
    <title>Login Page</title>
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <pre>${errorMessage}</pre>
    <form method="post">
        Name: <label>
        <input type="text" name="name">
    </label>
        Password: <label>
        <input type="password" name="password">
    </label>
        <input type="submit">
    </form>
</div>
</body>
</html>


<%--when we use POST method we send the information of password and username as part of Form data, otherwise if the method is GET
the info of pass and username will be send as part of the URL (not secured, we dont want this!) AFTER CLICKING SUBMIT BUTTON, METHOD TYPE IS POST !!!--%>