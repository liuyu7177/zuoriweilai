CREATE PROCEDURE `seckill`.`grabRedPacket` (
  IN v_redPacketId BIGINT,
  IN v_userId BIGINT,
  OUT r_result INT
)
BEGIN
  DECLARE
    insert_count INT DEFAULT 0;

  DECLARE
    amount DECIMAL (16, 2);

  SELECT
    Amount INTO amount
  FROM
    red_packet
  WHERE
      Id = v_redPacketId;

  START TRANSACTION;

  INSERT INTO user_red_packet (
    RedPacketId,
    UserId,
    Amount,
    GrabTime,
    Note
  )
  VALUES
  (
    v_redPacketId,
    v_userId,
    Amount,
    now(),
    CONCAT("抢红包",v_redPacketId)
  ) ;
  SELECT
    ROW_COUNT() INTO insert_count;


  IF (insert_count = 0) THEN
    ROLLBACK;


    SET r_result = - 1;


  ELSEIF (insert_count < 0) THEN
    ROLLBACK;


    SET r_result = - 2;


  ELSE
    UPDATE red_packet
    SET Stock = Stock - 1
    WHERE
        Id = v_redPacketId
      AND Stock > 0;
    SELECT
      ROW_COUNT() INTO insert_count;


    IF (INsert_count = 0) THEN
      ROLLBACK;
      SET r_result = 0;
    ELSEIF (insert_count < 0) THEN
      ROLLBACK;
      SET r_result = - 2;
    ELSE
      COMMIT;
      SET r_result = 1;
    END IF;
  END IF;
END