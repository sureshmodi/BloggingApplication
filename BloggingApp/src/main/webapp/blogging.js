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
		$.ajax({
			url : '/BloggingApp/webapi/blogging/user/login',
			type : 'post',
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			data : JSON.stringify(user),
			success : function(data) {
			    alert("You have successfully Logged in...!!!!!!");
			    var userId = data.userid;
			    if (localStorage) {
			    	localStorage.setItem('userId', userId);
			    } else {
			    	alert("browser doesnot support local storage");
			    }
			    document.getElementById("useridstrong").innerHTML = userId;
		        $("#headerlogin").hide();
		        $("#headerhome").show();
		        $("#headercreatepost").show();
		        $("#headerupdateprofile").show();
		        $("#headerlogout").show();
		        $("#headeruser").show();
		        $("#first").hide();
		        $("#bloglist").show();
		        document.getElementById("loginform").reset();
		        
			    var blogTable = $('#bloglisttable tbody');
			    
			    for (i = 0; i < data.links.length; i++) {
				    blogTable.append('<tr><td>' + data.links[i].reference + '</td><td>' + data.links[i].uri +'</td></tr>');
			    }			      

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
  	  document.getElementById("createpostform").reset();
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
	  $("#second").hide();
	  $("#first").show();
  });
/*
  $("a").click(function(event) {
      alert("As you can see, the link no longer took you to blogging uri");
      var href = $(this).attr('href');
      alert(href);
      event.preventDefault();
  });
  */
  $("#createblogpostsubmit").click(function() {
	  $('#createblogpost').hide();	  
	    if ($("#createblogposttitle").val() == '' || $("#createblogpostcontent").val() == '' || $("#createblogpostid").val() == '') {
	      alert("Please fill all fields...!!!!!!");
	    } else {
			var posttitle = $("#createblogposttitle").val();
			var postcontent = $("#createblogpostcontent").val();
			var postid = $("#createblogpostid").val();
			userid = localStorage.getItem('userId');
			alert(userid);
			var createpost = {
			  "blogpostid" : postid,
			  "title" : posttitle,
			  "blogContent" : postcontent
			};
		   
		    alert("sending ajax query...!!!!!!");
			$.ajax({
				url : '/BloggingApp/webapi/blogging/blogpost/userid/' + userid,
				type : 'post',
				dataType : 'json',
				contentType: "application/json; charset=utf-8",
				data : JSON.stringify(createpost),
				success : function(data) {
				    alert("Successfull created the blogpost...!!!!!!");
				    alert(data);
				    var blogTable = $('#bloglisttable tbody');
				    
				    blogTable.append('<tr><td>' + data.title + '</td><td> <a href="' + data.links[0].uri + '" class="blogpostdetails"> Show Details </a></td></tr>');
				    
				    /*working*/
				    //blogTable.append('<tr><td>' + data.title + '</td><td> <input class="blogpostdetails" type="button" value="Show Details"></td></tr>');
				},
			    statusCode: {
	   	            500: function() {
				      alert('Internal server code error');
				      document.getElementById("loginform").reset();
				    }
			    }		    
			});
	    }
	  $('#bloglist').show();
  });
  
  $("#home").click(function() {
	  $('#createblogpost').hide();
	  $('#displayblogpost').hide();
	  $('#first').hide();
	  $('#second').hide();
	  $('#bloglist').hide();

	    $('#bloglisttable tbody').empty();
	    alert("sending ajax query...!!!!!!");
		$.ajax({
			url : '/BloggingApp/webapi/blogging/blogpost/all',
			type : 'GET',
			dataType : 'json',
			contentType: "application/json; charset=utf-8",	  			
			success : function(data) {
     		    alert("Fetched all user posts successfully!!!");
			    alert(data);
			    var blogTable = $('#bloglisttable tbody');
			    
			    for (i = 0; i < data.links.length; i++) {
			    	blogTable.append('<tr><td>' + data.links[i].reference + '</td><td> <a href="' + data.links[i].uri + '" class="blogpostdetails"> Show Details </a></td></tr>');
				    //blogTable.append('<tr><td>' + data.links[i].reference + '</td><td>' + data.links[i].uri +'</td></tr>');
			    }			      
			},
		    statusCode: {
   	            500: function() {
			      alert('Internal server code error');
			    }
		    }
		});	  

	  $('#bloglist').show();
  });

  $("#displayposts").click(function() {
	  $('#createblogpost').hide();
	  $('#displayblogpost').hide();
	  $('#first').hide();
	  $('#second').hide();
	  $('#bloglist').hide();

	    alert("sending ajax query...!!!");
	    $('#bloglisttable tbody').empty();
		$.ajax({
			url : '/BloggingApp/webapi/blogging/blogpost/all',
			type : 'get',
			dataType : 'json',
			contentType: "application/json; charset=utf-8",	  
			success : function(data) {
				alert("Fetched all posts successfully!!!");
			    alert(data);
			    var blogTable = $('#bloglisttable tbody');
			    
			    for (i = 0; i < data.links.length; i++) {
			    	blogTable.append('<tr><td>' + data.links[i].reference + '</td><td> <a href="' + data.links[i].uri + '" class="blogpostdetails"> Show Details </a></td></tr>');
				    //blogTable.append('<tr><td>' + data.links[i].reference + '</td><td>' + data.links[i].uri +'</td></tr>');
			    }			      
			},
		    statusCode: {
   	            500: function() {
			      alert('Internal server code error');
			    }
		    }
		});		
		    		    
	  $('#bloglist').show();
  });


  $("#bloglisttable").on('click', '.blogpostdetails', function(event) {
	    alert("Caught dynamic row element !!!");
        var href = $(this).attr('href');
	    alert(href);
	    event.preventDefault();

		$('#bloglist').hide();
	    alert("row" + $(this).closest('table').index() + "Data Submitted" );
		$('#displayblogpost').show();
	});
  
  $('#blogpostdelete').click(function(){
	  $('#bloglist').hide();
	  alert("Post deleted successfully!!!");	  
	  $('#bloglist').show();
  });


  $('#logout').click(function(){
	  alert("Succesfully logged out !!!");
	  $('#bloglist').hide();
	  $('#createblogpost').hide();
	  $('#displayblogpost').hide();
      $("#headercreatepost").hide();
      $("#headerhome").hide();
      $("#headerlogin").show();
      $("#headerlogout").hide();
      $("#headerupdateprofile").hide();
      $("#headeruser").hide();
	  $('#first').show();
  });
  
});