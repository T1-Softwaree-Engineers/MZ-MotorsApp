<?php
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');
	header('Access-Control-Allow-Headers: X-Requested-With');
	header('Content-Type: application/json');

	include_once 'db.php';
	
	$post = new Database();
	$dir = "../mzmotors/users/";

	$api = $_SERVER['REQUEST_METHOD'];

	$id = intval($_GET['id'] ?? '');

	/*if ($api == 'GET') {
		
	  if ($id != 0) {
	    $data = $user->fetch($id);
	  } else {
	    $data = $user->fetch();
	  }
	  echo json_encode($data);
	}*/

	if ($api == 'GET') {
		
		$email = $post->test_input($_GET['email']);
		echo json_encode($post->getPublicaciones($email));
		/*$last_id = $post->getPostId($email);
		$last = $last_id[0]['id_post'];
		echo $last;*/
	}

	if ($api == 'POST') {
		
		$email = $post->test_input($_POST['Email']);
		$titulo = $post->test_input($_POST['Title']);
		$condicion = $post->test_input($_POST['Condition']);
        $año = $post->test_input($_POST['Year']);
        $marca = $post->test_input($_POST['Brand']);
        $modelo = $post->test_input($_POST['Model']);
        $features = $post->test_input($_POST['Features']);
        $ubicacion = $post->test_input($_POST['Location']);
        $precio = $post->test_input($_POST['Price']);
        $descripcion = $post->test_input($_POST['Description']);

        $directorioActual= getcwd();
        		
		$last_id = $post->getPostId($email);
		
		$last = $last_id[0]['id_post'];
		
        $last = $last + 1;
            
		mkdir("users/" .$email . "/" . $last, 0777);
			
	    echo "Directorio creado";
	    
	    //Crear rutas de las imagenes
		
		$path = "users/$email/$last";
		$actualPath = "https://ochoarealestateservices.com/mzmotors/$path";

		if ($post->insertPost($email, $actualPath, $titulo, $marca, $modelo, $año, $precio, $ubicacion, $features, $condicion, $descripcion)) {
		
			echo $post->message('Post added successfully!',false);
			//echo "Se guardo la Imagen correctamente"
		} else {
			echo $post->message('Failed to add an Post!',true);
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
	    //parse_str(file_get_contents('php://input'), $post_input);
        if ($id != null) {
            if ($post->updateMarkSold($id)) {
	            echo $post->message('User updated successfully!',false);
    	    }else{
	            echo $post->message('Failed to update an user!',true);
	        }
        }else{
            echo $post->message('User not found!', true);
        }
	 } 
	

	if ($api == 'DELETE') {
	  if ($id != null) {
	      $email = $_GET['email'];
	    if ($post->deletePublicaciones($id, $email)) {
	      echo $post->message('User deleted successfully!', false);
	    } else {
	      echo $post->message('Failed to delete an user!', true);
	    }
	  } else {
	    echo $post->message('User not found!', true);
	  }
	}

?>