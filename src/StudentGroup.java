import java.util.Date;

/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		return students;
	}

	@Override
	public void setStudents(Student[] students)throws IllegalArgumentException {
			if(students == null){
				throw (new IllegalArgumentException());
			}
			else{
				this.students = students;
			}
	}

	@Override
	public Student getStudent(int index)throws IllegalArgumentException {
			int len = students.length;
			if(index < 0 || index >= len){
				throw (new IllegalArgumentException());
			}
			else{
				return students[index];
			}
	}

	@Override
	public void setStudent(Student student, int index)throws IllegalArgumentException {
			int len = students.length;
			if(student == null || index < 0 || index >= len){
				throw (new IllegalArgumentException());
			}
			else{
				students[index] = student;
			}
	}

	@Override
	public void addFirst(Student student)throws IllegalArgumentException {
			if(student == null){
				throw (new IllegalArgumentException());
			}
			else{
				int len = students.length;
				Student[] std = new Student[len+1];
				std[0] = student;
				for(int i=1;i<=len;i++){
					std[i] = students[i-1];
				}
				this.students = std;
			}
	}

	@Override
	public void addLast(Student student)throws IllegalArgumentException {
			if(student == null){
				throw (new IllegalArgumentException());
			}
			else{
				int len = students.length;
				Student[] std = new Student[len+1];
				for(int i=0;i<len;i++){
					std[i] = students[i];
				}
				std[len] = student;
				this.students = std;
			}
	}

	@Override
	public void add(Student student, int index)throws IllegalArgumentException {
			int len = students.length;
			if(student == null || index < 0 || index >= len){
				throw (new IllegalArgumentException());
			}
			else{
				Student[] std = new Student[len+1];
				for(int i=0;i<index;i++){
					std[i] = students[i];
				}
				std[index] = student;
				for(int i=index+1;i<=len;i++){
					std[i] = students[i-1];
				}
				this.students = std;
			}
	}

	@Override
	public void remove(int index)throws IllegalArgumentException {
			int len = students.length;
			if(index < 0 || index >= len){
				throw (new IllegalArgumentException());
			}
			else{
				Student[] std = new Student[len-1];
				for(int i=0;i<index;i++){
					std[i] = students[i];
				}
				for(int i=index;i<len-1;i++){
					std[i] = students[i+1];
				}
				this.students = std;
			}
	}

	@Override
	public void remove(Student student)throws IllegalArgumentException {
		int len = students.length;
		if(len == 0){
			throw (new IllegalArgumentException("Student not exist"));
		}
		if(student == null){
			throw (new IllegalArgumentException());
		}
		int i =0;
		for(i=0;i<len;i++){
			if(students[i].id == student.id && students[i].fullName.equals(student.fullName) && students[i].birthDate.equals(student.birthDate) && students[i].avgMark == student.avgMark){
				break;
			}
		}
		if(i != len){
			remove(i);
		}
	}

	@Override
	public void removeFromIndex(int index)throws IllegalArgumentException {
		int len = students.length;
		if(index<0 || index>=len){
			throw (new IllegalArgumentException());
		}
		Student[] std = new Student[index+1];
		for(int i=0;i<=index;i++){
			std[i] = students[i];
		}
		this.students = std;
	}

	@Override
	public void removeFromElement(Student student)throws IllegalArgumentException {
		int len = students.length;
		if(student == null){
			throw (new IllegalArgumentException());
		}
		int i = 0;
		for(i=0;i<len;i++){
			if(students[i].id == student.id && students[i].fullName.equals(student.fullName) && students[i].birthDate.equals(student.birthDate) && students[i].avgMark == student.avgMark){
				break;
			}
		}
		if(i != len)
			removeFromIndex(i);
	}

	@Override
	public void removeToIndex(int index)throws IllegalArgumentException {
		int len = students.length;
		if(index<0 || index>=len){
			throw (new IllegalArgumentException());
		}
		Student[] std = new Student[len-index];
		for(int i=index;i<len;i++){
			std[i-index] = students[i];
		}
		this.students = std;
	}

	@Override
	public void removeToElement(Student student)throws IllegalArgumentException {
		int len = students.length;
		if(student == null){
			throw (new IllegalArgumentException());
		}
		int i=0;
		for(i=0;i<len;i++){
			if(students[i].id == student.id && students[i].fullName.equals(student.fullName) && students[i].birthDate.equals(student.birthDate) && students[i].avgMark == student.avgMark){
				break;
			}
		}
		if(i!=len){
			removeToIndex(i);
		}
	}

	@Override
	public void bubbleSort() {
		Student std;
		int len = students.length;
		for(int i=0;i<len-1;i++){
			for(int j=0;j<len-i-1;j++){
				if(students[j].id > students[j+1].id){
					std = students[j];
					students[j] = students[j+1];
					students[j+1] = std;
				}
			}
		}
	}

	@Override
	public Student[] getByBirthDate(Date date)throws IllegalArgumentException {
		if(date.equals(null))
			throw (new IllegalArgumentException());
		int len = students.length;
		Student[] std = new Student[len];
		int index = 0,i=0;
		for(i=0;i<len;i++){
			if(students[i].birthDate.compareTo(date) <= 0){
				std[index] = students[i];
				index++;
			}
		}
		if(index != len)
			return removeFromIndex(std,i);
		return students;
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		if(firstDate.equals(null) || lastDate.equals(null))
			throw (new IllegalArgumentException());
		int len = students.length;
		Student[] std = new Student[len];
		int index = 0,i=0;
		for(i=0;i<len;i++){
			if(students[i].birthDate.compareTo(firstDate) >= 0 && students[i].birthDate.compareTo(lastDate) <= 0){
				std[index] = students[i];
				index++;
			}
		}
		if(index != len)
			return removeFromIndex(std,i);
		return students;
	}

	@Override
	public Student[] getNearBirthDate(Date date, int days)throws IllegalArgumentException {
		return null;
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent)throws IllegalArgumentException {
		int len = students.length;
		if(indexOfStudent <= 0 || indexOfStudent > len){
			throw (new IllegalArgumentException());
		}
		long ageInMillis = new Date().getTime() - students[indexOfStudent].birthDate.getTime();
		int age = new Date(ageInMillis).getYear();
		return age;
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		int len = students.length;
		Student[] std = new Student[len];
		int index = 0;
		for(int i=0;i<len;i++){
			if(getCurrentAgeByDate(i) == age){
				std[index] = students[i];
				index++;
			}
		}
		if(index != len)
			return removeFromIndex(std,index);
		return students;
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		int len = students.length;
		double avg = -1;
		for(int i=0;i<len;i++){
			if(students[i].avgMark > avg){
				avg = students[i].avgMark;
			}
		}
		Student[] std = new Student[len];
		int index = 0;
		for(int i=0;i<len;i++){
			if(students[i].avgMark == avg){
				std[index] = students[i];
				index = index+1;
			}
		}
		if(index!=len){
			return removeFromIndex(std,index);
		}
		return students;
	}

	public Student[] removeFromIndex(Student[] student,int index) throws IllegalArgumentException{
		int len = students.length;
		if(student == null || index<0 || index >= len){
			throw (new IllegalArgumentException());
		}
		Student[] stdt = new Student[index+1];
		for(int i=0;i<=index;i++){
			stdt[i] = student[i];
		}
		return stdt;
	}

	@Override
	public Student getNextStudent(Student student)throws IllegalArgumentException {
		int len = students.length;
		if(student == null){
			throw (new IllegalArgumentException());
		}
		int i;
		for(i=0;i<len;i++){
			if(students[i].id == student.id && students[i].fullName == student.fullName && students[i].birthDate == student.birthDate && students[i].avgMark == student.avgMark){
				break;
			}
		}
		if(i!=len && i != len-1){
			return students[i+1];
		}
		return null;
	}
}