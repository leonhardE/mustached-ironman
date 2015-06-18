<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>resteasy-jsapi</title>
<!-- This will include the whole javascript file including ajax handling  -->
<script lang="javascript" src="./js"></script>

<!-- Write the javascript code for accessing REST APIs -->
<script lang="javascript">
	function addUserForm() {
		//Collect input from html page
		var firstNameTxt = document.getElementById("firstName").value;
		var lastNameTxt = document.getElementById("lastName").value;

		//Call the REST APIs with directly method names
		var message = UserService.addUser({
			firstName : firstNameTxt,
			lastName : lastNameTxt
		});

		//Use the REST API response
		document.getElementById("error").innerHTML = "<h2><span style='color:red'>"
				+ message + " !!</span></h2>";
	}
</script>
</head>
<body>
	<h1>RESTEasy Ajax client demo</h1>
	<div id="error"></div>
	<form method="post">
		<p>
			First Name : <input type="text" name="firstName" id="firstName" />
		</p>
		<p>
			LastName : <input type="text" name="lastName" id="lastName" />
		</p>
		<input type="button" value="Add User" onclick="addUserForm()" />
	</form>
	Demo by :
	<b>http://www.howtodoinjava.com</b>
</body>
</html>