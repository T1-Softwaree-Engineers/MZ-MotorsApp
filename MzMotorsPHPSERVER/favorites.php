<?php
	
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Methods: GET, POST, PUT, DELETE');
	header('Access-Control-Allow-Headers: X-Requested-With');
	header('Content-Type: application/json');

	require_once('db.php');
	
	$fav = new Database();

	$api = $_SERVER['REQUEST_METHOD'];
	
	
	if($api == 'POST'){
    	if ($fav->insertFavorito($_POST['idpost'], $_POST['email'])) {
		
			echo $fav->message('Post added successfully!',false);
			//echo "Se guardo la Imagen correctamente"
		} else {
			echo $fav->message('Failed to add an Post!',true);
		}
    	
	}

?>