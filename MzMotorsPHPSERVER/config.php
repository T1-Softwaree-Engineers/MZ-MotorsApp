<?php
	class Config {

	  private const DBHOST = 'localhost';
	  private const DBUSER = '';
	  private const DBPASS = '';
	  private const DBNAME = '';

	  private $dsn = 'mysql:host=' . self::DBHOST . ';dbname=' . self::DBNAME . '';

	  protected $conn = null;

	  public function __construct() // Connection constructor
	  {
	    try 
		{
	      $this->conn = new PDO($this->dsn, self::DBUSER, self::DBPASS);
	      $this->conn->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
	    } 
		catch (PDOException $e) 
		{
	      die('Connection Failed : ' . $e->getMessage());
	    }

	    return $this->conn;
	  }

	  public function test_input($data) // Sanitize data
	  {
	    $data = strip_tags($data);
	    $data = htmlspecialchars($data);
	    $data = stripslashes($data);
	    $data = trim($data);
	    return $data;
	  }

	  public function message($content, $status) // Error message
	  {
	    return json_encode(['message' => $content, 'error' => $status]);
	  }
	}

?>
