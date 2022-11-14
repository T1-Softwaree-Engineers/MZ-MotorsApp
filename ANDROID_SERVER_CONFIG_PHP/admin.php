<?php
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');
	header('Access-Control-Allow-Headers: X-Requested-With');
	header('Content-Type: application/json');

	include_once 'db.php';
	
	$admin = new Database();

	$api = $_SERVER['REQUEST_METHOD']; // Detects the type of API requests
	$op_key = $_GET['key']; // OP Key helps to prevent fake or unauthorized requests
	
	if ($api == 'GET') // Get method helps to login, needs email, pwd and Admin API keylog
	{
		if ($op_key === '9137462850') // verify login key is correct
		{
			$email = $admin->test_input($_GET['email']);
			$password = $admin->test_input($_GET['pwd']);

			if (empty($email) || empty($password)) // If email and pass are empty, don't login
			{
				echo json_encode(array('message' => 'Incorrect email or password'));
			}
			else
			{
				$password = hash('sha512', $password);
				echo json_encode($admin->loginAdm($email, $password));
			}
		}
		else
		{
			echo json_encode(array('message' => 'No permissions.')); // Fake logs via URL without key doesn't log
		}
	}

/*
	$id = intval($_GET['id'] ?? '');

	if ($api == 'GET') {
		
	  if ($id != 0) {
	    $data = $user->fetch($id);
	  } else {
	    $data = $user->fetch();
	  }
	  echo json_encode($data);
	}
*/
/* 	
	if ($api == 'POST') 
		{
			$nombre = $user->test_input($_POST['nombre']);
			$email = $user->test_input($_POST['email']);
			$contacto = $user->test_input($_POST['contacto']);
			$password = $user->test_input($_POST['pwd']);
			$password = hash('sha512', $password);
	
			if ($user->insert($nombre, $email, $contacto, $password)) 
			{
				echo $user->message('User added successfully!',false);
			} 
			else 
			{
				echo $user->message('Failed to add an user!',true);
			}
		} 
*/	
/*	
	if ($api == 'POST') 
		{
			$email = $user->test_input($_POST['email']);
			$password = $user->test_input($_POST['pwd']);
			$password = hash('sha512', $password);
			if ($user->login($email, $password)) 
			{
				echo $user->message('User login successfully!',false);
			} 
			else 
			{
				echo $user->message('Failed to login !',true);
			}
		}
*/
/* 	
	if ($api == 'PUT')
		{
			parse_str(file_get_contents('php://input'), $post_input);

			$nombre = $user->test_input($post_input['nombre']);
			$email = $user->test_input($post_input['email']);
			$contacto = $user->test_input($post_input['contacto']);
			$password = $user->test_input($post_input['pwd']);
			$password = hash('sha512', $password);

			if ($id != null) {
				if ($user->update($nombre, $email, $contacto, $id)) {
				echo $user->message('User updated successfully!',false);
				} else {
				echo $user->message('Failed to update an user!',true);
				}
			} else {
				echo $user->message('User not found!',true);
			}
		} 
*/		
/* 	
	if ($api == 'DELETE') 
		{
			if ($id != null) {
			if ($user->delete($id)) {
				echo $user->message('User deleted successfully!', false);
			} else {
				echo $user->message('Failed to delete an user!', true);
			}
			} else {
			echo $user->message('User not found!', true);
			}
		} 
*/

?>