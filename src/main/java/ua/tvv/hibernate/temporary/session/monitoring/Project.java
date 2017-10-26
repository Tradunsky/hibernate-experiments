package ua.tvv.hibernate.temporary.session.monitoring;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.MDC;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import ua.tvv.hibernate.temporary.session.monitoring.model.Employee;
import ua.tvv.hibernate.temporary.session.monitoring.model.Skill;

import static com.google.common.collect.Lists.newArrayList;
import static ua.tvv.hibernate.OrmUtils.getFactory;
import static ua.tvv.hibernate.OrmUtils.transactional;

@Slf4j
public class Project {

    public static void main(String[] args) {
        MDC.put("uri", "/employees/100500");
        try (SessionFactory factory = getFactory(Employee.class, Skill.class);
             Session session = factory.openSession()) {
            transactional(() -> {
                Employee integrationDeveloper = new Employee(100500);
                Skill scrum = new Skill("scrum", integrationDeveloper);
                Skill ssis = new Skill("SSIS", integrationDeveloper);
                Skill sql = new Skill("SQL", integrationDeveloper);

                integrationDeveloper.setSkills(newArrayList(scrum, ssis, sql));
                session.persist(integrationDeveloper);
            }, session);
            session.clear();
//            transactional(()-> {
                Employee employee = session.get(Employee.class, 100500);

                session.close();

                List<Skill> skills = employee.getSkills();
                Skill theSkill = skills.iterator().next();
//                session.persist(employee);
//            }, session);
        } finally {
            MDC.clear();
        }
    }
}
