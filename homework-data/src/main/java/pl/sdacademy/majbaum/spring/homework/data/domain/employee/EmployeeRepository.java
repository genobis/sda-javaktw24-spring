package pl.sdacademy.majbaum.spring.homework.data.domain.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sdacademy.majbaum.spring.homework.data.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    //Znajdź według daty urodzenia w podanym przedziale
    Page<Employee> findByBirthDateBetween(
            LocalDate startBirthDate, LocalDate endBirthDate, Pageable pageable
    );

    //Znajdź UNIKALNE (join powoduje duplikację!) według daty urodzenia w podanym przedziale i z umiejętnościami w podanym zbiorze
    //Ze względu na specyfikę działania joina, realnie dopasowuje wg co najmniej jednej umiejętności
    Page<Employee> findDistinctByBirthDateBetweenAndSkillsNameIn(LocalDate startBirthDate, LocalDate endBirthDate, List<String> skills, Pageable pageable);

    //Zapytanie JPQL pozwala osiągnąć podobny efekt z założeniem, że ilość umiejętności musi się zgadzać ze wskazaną
    //To drobne utrudnienie, ale daje dodatkowe pole manewru - można np. ustalić stopień dopasowania (ile minimum umiejętności ma się zgadzać)
    @Query("select e from Employee e join e.skills s where s.name in :skills and (e.birthDate between :startBirthDate and :endBirthDate) group by e.id having count(e.id) = :skillsCount")
    Page<Employee> findAllByDatesAndAllSkills(LocalDate startBirthDate, LocalDate endBirthDate, List<String> skills, long skillsCount, Pageable pageable);
}
