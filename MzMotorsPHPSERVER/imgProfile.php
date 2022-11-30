<?php

	include_once 'db.php';
	
	$post = new Database();
	
	$email = $post->test_input($_POST['Email']);
	//$nomImagen = $_POST['nom'];
    $imagen = $_POST['imagen'];
   

    $path = "users/$email/nomImgP.jpg";
    
    if($post->update($path, $email)){
        file_put_contents($path, base64_decode($imagen));
        echo $path;
    }
    
	
?>