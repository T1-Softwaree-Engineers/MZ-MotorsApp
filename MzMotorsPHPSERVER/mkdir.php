<?php

	include_once 'db.php';
	
	$post = new Database();
	
	$email = $post->test_input($_POST['Email']);
	$nomImagen = $_POST['nom'];
    $imagen = $_POST['imagenes'];
	

    $directorioActual= getcwd();
        		
	$last_id = $post->getPostId($email);
		
	$last = $last_id[0]['id_post'];
            
		
	$path = "users/$email/$last/$nomImagen.png";
		
	file_put_contents($path, base64_decode($imagen));
?>