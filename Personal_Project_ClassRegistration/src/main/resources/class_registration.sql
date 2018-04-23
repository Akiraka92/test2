

drop table class_registration_list cascade constraints;
drop table class_subject cascade constraints;
drop table professors cascade constraints;
drop table students cascade constraints;
drop table majors cascade constraints;
drop table syllabus cascade constraints;
drop table class_application cascade constraints;


drop sequence reg_seq;
drop sequence lec_seq;

-- �к� ����
create table majors(
	 major		     varchar2(50)	 not null
	,building		 varchar2(50)
	,constraint majors_pk primary key(major)
);


-- ����
create table class_subject(
	 lecture_id		 number
	,credit			 number			 not null
	,lecture_name	 varchar2(50)	 not null
	,major			 varchar2(50)	 not null
    ,constraint lecture_pk primary key(lecture_id)
	,constraint lecture_fk foreign key(major)
		references majors(major)
);
-- �����ڵ� ������
create sequence lec_seq;

-- ����
create table professors(
	 professor_id    varchar2(50) 
	,professor_pw    varchar2(50)    not null
	,professor_name	 varchar2(50)    not null
	,major           varchar2(50)    not null
--	,security_level	number	default 100
	,constraint pro_pk primary key(professor_id)
	,constraint pro_fk foreign key(major)
		references majors(major)
);

-- ������û ���
create table class_registration_list(
	 class_no		 number
	,lecture_id		 number			 not null
	,professor_id	 varchar2(50)
	,schedule_day	 varchar2(20)	 not null
	,schedule_start	 number			 not null
	,schedule_end	 number			 not null
	,quota			 number			 default 30
    ,applicant       number           default 0
    ,constraint regist_day check(schedule_day in('mon', 'tue', 'wed', 'thu', 'fri'))
    ,constraint regist_time check(schedule_start >= 1 and schedule_end >= schedule_start)
    ,constraint regist_quota check(applicant >= 0 and quota >= applicant)
	,constraint regist_pk primary key(class_no)
	,constraint regist_fk foreign key(lecture_id)
		references class_subject(lecture_id)
    ,constraint regist_pro foreign key(professor_id)
        references professors(professor_id)
		on delete cascade
);
-- ������û �������� ������
create sequence reg_seq;

-- �л�
create table students(
	 student_id      varchar2(50)
	,student_pw      varchar2(50)     not null
	,student_name	 varchar2(50)     not null
	,major           varchar2(50)     not null
	,grade			 number			  not null
    ,tutor           varchar2(50)
--	,security_level	number	default 100
	,constraint stu_pk primary key(student_id)
	,constraint stu_fk foreign key(major)
		references majors(major)
    ,constraint std_pro foreign key(tutor)
        references professors(professor_id)
);

-- ���ǰ�ȹ��
create table syllabus(
     professor  varchar2(50)
    ,lecture_id number  
    ,content     varchar(1000)
    ,constraint syll_pro foreign key(professor)
        references professors(professor_id)
    ,constraint syll_lec foreign key(lecture_id)
        references class_subject(lecture_id)
    ,constraint syll_pk primary key(professor, lecture_id)
);

-- ������û ��Ȳ
create table class_application(
     class_no        number           not null
    ,student_id      varchar2(50)     not null
    ,constraint app_class foreign key(class_no)
        references class_registration_list(class_no)
    ,constraint student_id foreign key(student_id)
        references students(student_id)
    ,constraint app_pk primary key(class_no, student_id)
);


insert into majors(major) values('executive');
insert into majors(major) values('교양');

insert into professors(professor_id, professor_pw, professor_name, major)
	values('admin', 'admin', 'admin', 'admin');


