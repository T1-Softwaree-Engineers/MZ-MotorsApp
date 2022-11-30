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
	    
		echo json_encode($post->getAllPostsAuth());
		/*$last_id = $post->getPostId($email);
		$last = $last_id[0]['id_post'];
		echo $last;*/
	}


?>