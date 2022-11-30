<?php
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');
	header('Access-Control-Allow-Headers: X-Requested-With');
	header('Content-Type: application/json');

	include_once 'db.php';
	
	$user = new Database();


	$api = $_SERVER['REQUEST_METHOD'];

	/*$id = intval($_GET['id'] ?? '');

	if ($api == 'GET') {
		
	  if ($id != 0) {
	    $data = $user->fetch($id);
	  } else {
	    $data = $user->fetch();
	  }
	  echo json_encode($data);
	}*/

	if ($api == 'GET') {
	    if(isset($_GET['email']) && isset($_GET['pwd'])){
	        $email = $user->test_input($_GET['email']);
    		$password = $user->test_input($_GET['pwd']);
    		$password = hash('sha512', $password);
    		echo json_encode($user->login($email, $password));
	    }else if (isset($_GET['email'])){
	        $email = $user->test_input($_GET['email']);
	        echo json_encode($user->getInfoContactSeller($email));
	    }
		
	}

	if ($api == 'POST') {
		$nombre = $user->test_input($_POST['nombre']);
		$email = $user->test_input($_POST['email']);
		$contacto = $user->test_input($_POST['contacto']);
		$password = $user->test_input($_POST['pwd']);
		$password = hash('sha512', $password);
		
		$directorioActual= getcwd();
        $directorioNuevo= $email;
    
        if(mkdir($directorioActual. "/users/" . $directorioNuevo, 0777)) {
            echo "Directorio creado";
        } else {
            echo "Error al crear el directorio";
        }
        
		if ($user->insert($nombre, $email, $contacto, $password)) {
			echo $user->message('User added successfully!',false);
		} else {
			echo $user->message('Failed to add an user!',true);
		}
	}

	/*if ($api == 'POST') {
		$email = $user->test_input($_POST['email']);
		$password = $user->test_input($_POST['pwd']);
		$password = hash('sha512', $password);
		if ($user->login($email, $password)) {
		  echo $user->message('User login successfully!',false);
		} else {
		  echo $user->message('Failed to login !',true);
		}
	  }*/

    
	if ($api == 'PUT') {
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

	if ($api == 'DELETE') {
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

?>