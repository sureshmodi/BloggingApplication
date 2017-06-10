$(document).ready(function() {
  // On Click SignIn Button Checks For Valid E-mail And All Field Should Be Filled
  $("#login").click(function() {
    var email = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
    if ($("#loginuserid").val() == '' || $("#loginpassword").val() == '') {
      alert("Please fill all fields...!!!!!!");
    } else {
		var loginid = $("#loginuserid").val();
		var password = $("#loginpassword").val();
		var user = {
			"userid" : loginid,
			"password" : password
		};

		$.ajax({
			url : '/BloggingApp/webapi/blogging/user/login',
			type : 'post',
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			success : function(data) {
			    alert("You have successfully Logged in...!!!!!!");
				$("#addResult").show();
			},
			data : JSON.stringify(user)
		});
      $("form")[0].reset();
    }
  });
  
  $("#register").click(function() {
    var email = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
    if ($("#name").val() == '' || $("#registeremail").val() == '' || $("#registerpassword").val() == '' || $("#contact").val() == '') {
      alert("Please fill all fields...!!!!!!");
    } else if (!($("#registeremail").val()).match(email)) {
      alert("Please enter valid Email...!!!!!!");
    } else {
      var registeruserid = $("#registeruserid").val();
	  var registerpassword = $("#registerpassword").val();
	  var name = $("#name").val();
	  var contactno = $("#contact").val();
	  var registeremail = $("#registeremail").val();
      var address = $("#address").val();

	  var registeruser = {
	    emailid: registeremail,
	 	fullname: name,
		address: address,
		mobileno: contactno,
		userid: registeruserid,
		password: registerpassword
	  };
	  
	  $('#target').html('sending..');

	  $.ajax({
			url : '/BloggingApp/webapi/blogging/user/register',
			type : 'post',
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			success : function(data) {
		      alert("You have successfully Sign Up, Now you can login...!!!!!!");
		      $('#target').html(data.msg);
			},
			data : JSON.stringify(registeruser)
	  });

      $("#form")[0].reset();
      $("#second").slideUp("slow", function() {
        $("#first").slideDown("slow");
      });
    }
  });
  
  // On Click SignUp It Will Hide Login Form and Display Registration Form
  $("#signup").click(function() {
    $("#first").slideUp("slow", function() {
      $("#second").slideDown("slow");
    });
  });
  
  // On Click SignIn It Will Hide Registration Form and Display Login Form
  $("#signin").click(function() {
    $("#second").slideUp("slow", function() {
      $("#first").slideDown("slow");
    });
  });
});