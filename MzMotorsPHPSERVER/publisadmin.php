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
		echo json_encode($post->getPublicaciones($email));
		/*$last_id = $post->getPostId($email);
		$last = $last_id[0]['id_post'];
		echo $last;*/
	}


	if ($api == 'PUT') {
	    //parse_str(file_get_contents('php://input'), $post_input);
        if ($id != null) {
            if ($post->authPublicaciones($id)) {
	            echo $post->message('Publi autorizada successfully!',false);
    	    }else{
	            echo $post->message('Failed to update an user!',true);
	        }
        }else{
            echo $post->message('User not found!', true);
        }
     
    
	} 

?>