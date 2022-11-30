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
	
	    
	if ($api == 'PUT') {
	    //parse_str(file_get_contents('php://input'), $post_input);
        if ($id != null) {
            if ($post->unauthPublicaciones($id)) {
	            echo $post->message('Publi oculta successfully!',false);
    	    }else{
	            echo $post->message('Failed to update an user!',true);
	        }
        }else{
            echo $post->message('User not found!', true);
        }
	 }