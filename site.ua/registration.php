<html>
 <head>
 <meta  charset="cp1251">
  <title>Регистрация</title>
  </head>
  <body>
<table>
      <form action="verification.php" method="POST">
	    <tr>
      <td>Name:</td>
      <td><input type="text" size="20" name="firstName"></td>
      </tr>
      <tr>
      <td>Second name:</td>
      <td><input type="text" size="20" name="secondName"></td>
      </tr>
	   <tr>
      <td>Second name:</td>
      <td><input type="text" size="20" name="lastName"></td>
      </tr>
      <tr>
      <td>Login<font color="red">*</font>:</td>
      <td><input type="text" size="20" name="login" ></td>
      </tr>
      <tr>
      <td>Password<font color="red">*</font>:</td>
      <td><input type="password" size="20" maxlength="20" name="password" ></td>
      </tr>
      <tr>
      <td>repeat password<font color="red">*</font>:</td>
      <td><input type="password" size="20" maxlength="20" name="password2"></td>
      </tr>
      <tr>
      <td>E-mail<font color="red">*</font>:</td>
      <td><input type="text" size="20" name="email"></td>
      </tr>
     <tr>
      <td>Telephone<font color="red">*</font>:</td>
      <td><input type="text" size="20" name="telephone"></td>
      </tr>
	  <tr>
      <td>Adress<font color="red">*</font>:</td>
      <td><input type="text" size="20" name="adress"></td>
      </tr>
	  <tr>
      <td>City<font color="red">*</font>:</td>
      <td><input type="text" size="20" name="city"></td>
      </tr>
      <tr>
       <td></td>
      <td colspan="2"><input type="submit" value="Sing in..." name="submit" ></td>
      </tr>
     <br>
      </form>
      </table>
<font face="Verdana" size="4">Fields with pointer <font color="red">*</font> must be written!</font> 
<br><a href='index.php'>Home</a>
 </body>
 </html>
