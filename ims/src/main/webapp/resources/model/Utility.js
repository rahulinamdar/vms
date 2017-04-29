Utility={
	login:function(){
		var user = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		
		$.ajax({
			type: 'POST',
			url: "../login?userName="+user+"&password="+password+"",
			error: function(data) {
			console.log(data);
			document.getElementById("login_message").value = "Login failed please check username and password";
			},
			dataType: 'json',
			success: function(data) {
				if(data.status){
					document.cookie = "username="+data.username+"; path=/;";
					document.cookie = "region="+data.region+"; path=/;";
				localStorage["username"] = data.username;
				localStorage["region"] = data.region;
				location.href="../";
				}else{
					document.getElementById("login_message").innerHTML = "Login failed please check username and password";
					document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
					document.cookie = "region=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
					localStorage.clear();
				}
			}
			});
	}
		
};