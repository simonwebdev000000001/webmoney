<?php
    include_once("bd.php");
	
    if (isset($_POST['submit']))
	{
		if(empty($_POST['login']))  {
			echo '<br><font color="red"><img border="0" src="error.gif" align="middle" alt="Введите логин!"> Введите логин! </font>';
		} 
		elseif (!preg_match("/^\w{3,}$/", $_POST['login'])) {
			echo '<br><font color="red"><img border="0" src="error.gif" align="middle" alt="В поле "Логин" введены недопустимые символы!"> В поле "Логин" введены недопустимые символы! Только буквы, цифры и подчеркивание!</font>';
		}
		elseif(empty($_POST['password'])) {
			echo '<br><font color="red"><img border="0" src="error.gif" align="middle" alt="Введите пароль !"> Введите пароль!</font>';
		}
		/*elseif (!preg_match("/\A(\w){6,20}\Z/", $_POST['password'])) {
			echo '<br><font color="red"><img border="0" src="error.gif" align="middle" alt="Пароль слишком короткий!"> Пароль слишком короткий! Пароль должен быть не менее 6 символов! </font>';
		}
		elseif(empty($_POST['password2'])) {
			echo '<br><font color="red"><img border="0" src="error.gif" align="middle" alt="Введите подтверждение пароля!"> Введите подтверждение пароля!</font>';
		}
		elseif($_POST['password'] != $_POST['password2']) {
			echo '<br><font color="red"><img border="0" src="error.gif" align="middle" alt="Введенные пароли не совпадают!"> Введенные пароли не совпадают!</font>';
		}
		elseif(empty($_POST['email'])) {
			echo '<br><font color="red"><img border="0" src="error.gif" align="middle" alt="Введите E-mail!">Введите E-mail! </font>';
		}*/
		elseif (!preg_match("/^[a-zA-Z0-9_\.\-]+@([a-zA-Z0-9\-]+\.)+[a-zA-Z]{2,6}$/", $_POST['email'])) {
			echo '<br><font color="red"><img border="0" src="error.gif" align="middle" alt="E-mail имеет недопустимий формат!"> E-mail имеет недопустимий формат! Например, name@gmail.com! </font>';
		}
		 
		else{
			$login = $_POST['login'];
			$password = $_POST['password'];
			$salt = rand(100,999);
			$mdPassword = md5(md5($password).$salt);
			$password2 = $_POST['password2'];
			$email = $_POST['email'];
			$rdate = date("d-m-Y в H:i");
			$firstName = $_POST['firstName'];
			$secondName = $_POST['secondName'];
			$lastname = $_POST['lastName']; 
				$adress = $_POST['adress'];
				$city =$_POST['city'];
				$telephone =$_POST['telephone'];
				$creditStatus="0";
				$f = rand(10000,99999);
				$wmid = rand(10000,99999).rand(10000,99999).rand(10000,99999);
				$level="First";
				$nameTable = "dataPurse".rand(100,999);
			  
			$query = ("SELECT id FROM datatable WHERE login='$login'");
			$sql = mysql_query($query) or die(mysql_error());
			
			if (mysql_num_rows($sql) > 0) {
				echo '<font color="red"><img border="0" src="error.gif" align="middle" alt="Пользователь с таким логином зарегистрированый!"> Пользователь с таким логином зарегистрирован!</font>';
			}
			else {
				$query2 = ("SELECT id FROM datatable WHERE email='$email'");
				$sql = mysql_query($query2) or die(mysql_error());
				if (mysql_num_rows($sql) > 0){
					echo '<font color="red"><img border="0" src="error.gif"  alt="Пользователь с таким e-mail зарегистрированый!"> Пользователь с таким e-mail уже зарегистрирован!</font>';
				}
				else{
					$query = "INSERT INTO dataTable (firstName, secondName, lastName, adress, city, telephone, email, level, creditStatus, wmid, login, password, reg_date, salt, nameTable )
							  VALUES ('$firstName', '$secondName', '$lastname', '$adress', '$city', '$telephone', '$email', '$level', '$creditStatus', '$wmid', '$login', '$mdPassword', '$rdate', '$salt', '$nameTable')";
					$result = mysql_query($query) or die(mysql_error());
					$query ="CREATE TABLE `webmoney`.`".$nameTable."` (`id` INT NOT NULL AUTO_INCREMENT ,`name` VARCHAR( 20 ) NOT NULL ,`summa` VARCHAR( 20 ) NOT NULL ,`number` VARCHAR( 20 ) NOT NULL ,`date` VARCHAR( 20 ) NOT NULL, PRIMARY KEY ( `id`  ) ) ENGINE = MYISAM";
						$result = mysql_query($query) or die(mysql_error());
					echo '<font color="green"><img border="0" src="ok.gif" align="middle" alt="Вы успешно зарегистрировались!"> We meet at last!</font><br><a href="index.php">На главную</a>';
					
}}}  }?>
