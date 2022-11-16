<?php
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');
	header('Access-Control-Allow-Headers: X-Requested-With');
	header('Content-Type: application/json');

	include_once 'db.php';
	
	$post = new Database();


	$api = $_SERVER['REQUEST_METHOD'];
	$option_query = $_GET['option'];

	/*$id = intval($_GET['id'] ?? '');

	if ($api == 'GET') {
		
	  if ($id != 0) {
	    $data = $user->fetch($id);
	  } else {
	    $data = $user->fetch();
	  }
	  echo json_encode($data);
	}*/

	if ($api === 'GET' && $option_query === 'unique' && isset($_GET['email']))
	{
		$email = $post->test_input($_GET['email']);
		echo json_encode($post->getPublicaciones($email));
	}

	if($api === 'GET' && $option_query === 'all')
	{
		echo json_encode($post->getAllPosts());
	}


	if ($api == 'POST') {
		$email = $post->test_input($_POST['Email']);
		$imagen = $_POST['Foto'];
		$titulo = $post->test_input($_POST['Title']);
		$condicion = $post->test_input($_POST['Condition']);
        $año = $post->test_input($_POST['Year']);
        $marca = $post->test_input($_POST['Brand']);
        $modelo = $post->test_input($_POST['Model']);
        $features = $post->test_input($_POST['Features']);
        $ubicacion = $post->test_input($_POST['Location']);
        $precio = $post->test_input($_POST['Price']);
        $descripcion = $post->test_input($_POST['Description']);

		$fNombre = "Xbox";
		$path = "images/$fNombre.jpg";
		$actualPath = "https://ochoarealestateservices.com/mzmotors/$path";

		if ($post->insertPost($email, $actualPath, $titulo, $marca, $modelo, $año, $precio, $ubicacion, $features, $condicion, $descripcion)) {
			file_put_contents($path, base64_decode($imagen));
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
	  parse_str(file_get_contents('php://input'), $post_input);

	  $nombre = $post->test_input($post_input['nombre']);
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