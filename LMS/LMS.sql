use lms;
/* given the track name learners who are associated with the track.
 If learner is enrolled in a course then it is indirectly associated with the track*/
 
 -- manual mapping
 select l.*
 from learner l, enroll e, course c, track t
 where l.id = e.learner_id
 AND e.course_id = c.id
 AND c.track_id = (select id from track  where name ="JAVA DEV");

 -- implement same things with joins 
select l.*
from learner l 
JOIN enroll e ON l.id = e.learner_id
JOIN course c ON e.course_id = c.id
JOIN track t ON c.track_id = t.id
where t.name="JAVA DEV";
-- advantage of joins over manual mapping : hibernate/jpa uses joins

/* using group by disply ststs for no of learners enrolled in each course
course name     no_of_students_enrolled
core_java               12
springboot              7
*/
SELECT  c.title AS course_name,
    COUNT(e.learner_id) AS number_of_students_enrolled
FROM  enroll e
JOIN   course c ON e.course_id = c.id
GROUP BY   c.title;