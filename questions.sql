1. Query to an entity  to make sure the json file including the key:
http://stackoverflow.com/questions/32671419/jpql-query-with-relation-table-to-json/32676455
class StuInfoWithTeacherName {
  private long id;
  private String stuName;
  private int stuAge;
  private String teacherName;
  public StuInfoWithTeacherName(id, stuName, stuAge, teacherName){
    //omitted
  }
}

select new StuInfoWithTeacherName(s.stuId, s.stuName, s.stuAge, t.teachName) FROM Student s JOIN s.teacher t WHERE s.teacher.teachName= t.teachName


-------*************

http://stackoverflow.com/questions/36328063/how-to-return-a-custom-object-from-a-spring-data-jpa-group-by-query
Step 1: Declare a simple bean class

public class SurveyAnswerStatistics {
  private String answer;
  private Long   cnt;

  public SurveyAnswerStatistics(String answer, Long cnt) {
    this.answer = answer;
    this.count  = cnt;
  }
}
Step 2: Return bean instances from the repository method
@Query(value = "select new com.path.to.class.SurveyAnswerStatistics(v.answer, count(v)) from Survey v group by v.answer")
List<SurveyAnswerStatistics> findSurveyCount();
---@JsonIdentityInfo 
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class)

-------------------------------------------------


#color:
#89949B
#2B2B2B
#B4CF2A
rgba(150, 81, 150, 1.00)
rgba(128, 41, 136, 1.00)    #802988





----------------------------------
Txtmark    https://github.com/rjeschke/txtmark 
PageDown
https://github.com/AlexP11223/JavaSpringMvcBlog 





