<?php

	include_once 'config.php';

	class Database extends Config {
	  public function fetch($id = 0) {
	    $sql = 'SELECT * FROM usuarios';
	    if ($id != 0) {
	      $sql .= ' WHERE id = :id';
	    }
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['id' => $id]);
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }

	  public function insert($nombre, $email, $contacto, $password) {
	    $sql = 'INSERT INTO usuarios (nombre, email, contacto, pwd, rol) VALUES (:nombre, :email, :contacto, :pwd, "0")';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['nombre' => $nombre, 'email' => $email, 'contacto' => $contacto, 'pwd' => $password]);
	    return true;
	  }

	  public function login($email, $password) {
	    $sql = 'SELECT * FROM usuarios WHERE email = :email AND pwd = :pwd';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['email' => $email, 'pwd' => $password]);
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }
	  
	  public function loginAdm($email, $password)
	  {
		$sql = 'SELECT * FROM usuarios WHERE email = :email AND pwd = :pwd AND rol = 1';
		$stmt = $this->conn->prepare($sql);
		$stmt->execute(['email' => $email, 'pwd' => $password]);
		$rows = $stmt->fetchAll();
		return $rows;
	  }

	  public function update($path, $email) {
	    $sql = 'UPDATE usuarios SET fperfil = :path WHERE email = :email';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['path' => $path, 'email' => $email]);
	    return true;
	  }

	  public function delete($id) {
	    $sql = 'DELETE FROM usuarios WHERE id = :id';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['id' => $id]);
	    return true;
	  }

	  public function insertPost($email, $actualPath, $titulo, $marca, $modelo, $año, $precio, $ubicacion, $features, $condicion, $descripcion) {
		$sql = 'INSERT INTO publicaciones (email_user, photos, titulo, marca, modelo, año, precio, ubicacion, features, condicion, descripcion, autorizada, vendida) VALUES (:email, :foto, :titulo, :marca, :modelo, :ano, :precio, :ubicacion, :features, :condicion, :descripcion, "0", "0")';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['email' => $email, 'foto'=>$actualPath, 'titulo' => $titulo, ':marca' => $marca, 'modelo' => $modelo, 'ano' => $año, 'precio' => $precio, 'ubicacion' => $ubicacion, 'features' => $features, 'condicion' => $condicion, 'descripcion' => $descripcion]);
	    return true;
	  }
	  
	  
      public function insertFavorito($idpost, $email)
	  {
		$sql = 'INSERT INTO favoritos (id_post, email_user) VALUES (:id, :email)';
		$stmt = $this->conn->prepare($sql);
		$stmt->execute(['id' => $idpost, 'email' => $email]);
		return true;
	  } 

	  public function getPublicaciones($email) {
	    $sql = 'SELECT * FROM publicaciones WHERE email_user = :email';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['email' => $email]);
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }
	  
	  public function getInfoContactSeller($email) {
	    $sql = 'SELECT nombre, contacto FROM usuarios WHERE email = :email';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['email' => $email]);
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }

	  public function getPostId($email)
	  {
		$sql = 'SELECT id_post FROM publicaciones ORDER BY id_post DESC LIMIT 0, 1';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['email' => $email]);
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }
	  
	  public function deletePublicaciones($id, $email) {
	    $sql = 'DELETE FROM publicaciones WHERE id_post = :id';
	    $sql2 = 'ALTER TABLE publicaciones AUTO_INCREMENT = 1';
	    $stmt = $this->conn->prepare($sql);
	    $stmt2 = $this->conn->prepare($sql2);
	    $stmt->execute(['id' => $id]);
	    $stmt2->execute();
	    $files = scandir("users/$email/$id");
	    foreach ($files as $file) {
            if($file != '.' && $file != '..'){
                unlink("users/$email/$id".'/'.$file);
            }
        }
        rmdir("users/$email/$id");
	    return true;
	  }
	  
	  public function updateMarkSold($id) {
	    $sql = 'UPDATE publicaciones SET vendida = 1 WHERE id_post = :id_post';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['id_post' => $id]);
	    return true;
	  }
	  
	  public function updatePublicaciones($path, $id) {
	    $sql = 'UPDATE publicaciones SET photos = :path WHERE id = :id';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['path' => $path, 'id' => $id]);
	    return true;
	  }
	  
	  	public function searchPublicaciones($q,$q2) {
	    $sql = 'SELECT * FROM publicaciones WHERE modelo LIKE :q OR marca LIKE :q2';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['q' => $q, 'q2' => $q2]);
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }
	  
  	  public function getAllPosts()
	  {
		$sql = 'SELECT * FROM publicaciones ORDER BY vendida DESC';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute();
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }
	  
	  public function getAllPostsAuth()
	  {
		$sql = 'SELECT * FROM publicaciones WHERE autorizada = 0 AND vendida = 0';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute();
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }
	  
	  public function getAllAuthorizedPosts()
	  {
		$sql = 'SELECT * FROM publicaciones WHERE autorizada = 1';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute();
	    $rows = $stmt->fetchAll();
	    return $rows;
	  }
	  
	  public function getAllFavPost($email)
	  {
	     $sql = 'SELECT * FROM favoritos INNER JOIN publicaciones ON publicaciones.id_post = favoritos.id_post WHERE favoritos.email_user = :email';
	     $stmt = $this->conn->prepare($sql);
	     $stmt->execute(['email' => $email]);
	     $rows = $stmt->fetchAll();
	     return $rows;
	  }
	  
	  public function authPublicaciones($id) {
	    $sql = 'UPDATE publicaciones SET autorizada = 1 WHERE id_post = :id_post';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['id_post' => $id]);
	    return true;
	  }
	  
  	  public function unauthPublicaciones($id) {
	    $sql = 'UPDATE publicaciones SET autorizada = 0 WHERE id_post = :id_post';
	    $stmt = $this->conn->prepare($sql);
	    $stmt->execute(['id_post' => $id]);
	    return true;
	  }
	  
	}
	
	
	

?>