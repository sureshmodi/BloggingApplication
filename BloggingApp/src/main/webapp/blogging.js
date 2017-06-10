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
	   
	    alert("sending ajax query...!!!!!!");
		//$('#target').html('sending..');
		$.ajax({
			url : '/BloggingApp/webapi/blogging/user/login',
			type : 'post',
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(user),
			success : function(data) {
			    alert("You have successfully Logged in...!!!!!!");
		        //$("#first").slideUp("slow");
		        $("#headerlogin").hide();
		        $("#headerhome").show();
		        $("#headercreatepost").show();
		        $("#headerupdateprofile").show();
		        $("#headerlogout").show();
		        $("#first").hide();
		        //$("#second").hide();
		        $("#bloglist").show();
		        document.getElementById("loginform").reset();
			},
		    statusCode: {
		        401: function() {
		          alert('Either User ID or Password is incorrect. Please Signup or Login again!!!');
		          document.getElementById("loginform").reset();
		        },
		        400: function() {
		          alert('bad request');
		          document.getElementById("loginform").reset();
		        },
   	            500: function() {
			      alert('bad request');
			      document.getElementById("loginform").reset();
			    }
		    }
		    
		});
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
			data : JSON.stringify(registeruser),
			success : function(data) {
		      alert("You have successfully Sign Up, Now you can login...!!!!!!");
		      //$('#target').html(data.msg);
		      $("#form")[0].reset();
		      $("#second").slideUp("slow", function() {
		        $("#first").slideDown("slow");
		      });
			},
		    statusCode: {
		        409: function() {
		          alert('User Id already exists. User different User id for registration...!!!');
		          $("#form")[0].reset();
		        },
		        400: function() {
		          alert('bad request');
		          $("#form")[0].reset();
		        },
   	            500: function() {
			      alert('bad request');
			      $("#form")[0].reset();
			    }	        
		    }

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
  
  $('#createpost').click(function(){
	  $('#bloglist').hide();
	  $('#displayblogpost').hide();
	  $('#createblogpost').show();
  });

  $('#updateprofile').click(function(){
	  $('#bloglist').hide();
	  $('#displayblogpost').hide();
	  $('#createblogpost').hide();
	  //Using GET method get the details
	  //Display user details
	  alert("profile updated successfully!!!");
  });
  
  $("#headerloginform").click(function(){
	  $("#bloglist").hide();
	  $("#displayblogpost").hide();
	  //$('#createblogpost').hide();
	  //Using GET method get the details
	  //Display user details
	  $("#second").hide();
	  $("#first").show();
  });

  
  $("#createblogpostsubmit").click(function() {
	  $('#createblogpost').hide();
	  alert("Post created successfully!!!");
	  $('#bloglist').show();
  });
  
  $("#home").click(function() {
	  $('#createblogpost').hide();
	  $('#displayblogpost').hide();
	  $('#first').hide();
	  $('#second').hide();
	  $('#bloglist').hide();
	  //$("#displayposts").hide();
	  alert("Fetched all posts successfully!!!");
	  $('#bloglist').show();
  });

  $("#displayposts").click(function() {
	  $('#createblogpost').hide();
	  $('#displayblogpost').hide();
	  $('#first').hide();
	  $('#second').hide();
	  $('#bloglist').hide();
	  alert("Fetched all posts successfully!!!");
	  $('#bloglist').show();
  });


  $('#blogpostdetails').click(function(){
	  $('#bloglist').hide();
	  alert("Fetched blog post details successfully!!!");
	  $('#displayblogpost').show();
  });
  
  $('#blogpostdelete').click(function(){
	  $('#bloglist').hide();
	  alert("Post deleted successfully!!!");	  
	  $('#bloglist').show();
  });


  $('#logout').click(function(){
	  alert("Succesfully logged out !!!");
	  //$('#blogheader').hide();
	  $('#bloglist').hide();
	  $('#createblogpost').hide();
	  $('#displayblogpost').hide();
      $("#headercreatepost").hide();
      $("#headerhome").hide();
      $("#headerlogin").show();
      $("#headerlogout").hide();
      $("#headerupdateprofile").hide();
	  $('#first').show();
  });
  
});