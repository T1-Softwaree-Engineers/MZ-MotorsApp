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
	

    if ($api == 'GET') {
        
	    $email = $post->test_input($_GET['email']);
	    
		echo json_encode($post->getAllFavPost($email));
	}

?>