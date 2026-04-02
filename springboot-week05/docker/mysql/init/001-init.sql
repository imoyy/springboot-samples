CREATE TABLE IF NOT EXISTS user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(128) NOT NULL,
    age INT,
    email VARCHAR(128),
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO user (username, password, age, email)
SELECT 'demo_user', '123456', 20, 'demo@example.com'
WHERE NOT EXISTS (
    SELECT 1
    FROM user
    WHERE username = 'demo_user'
);
