package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class EmployeeRowMapper<T> implements RowMapper<T> {

    @Override
    public T mapRow(ResultSet resultSet) {
        try {
            BigInteger id = BigInteger.valueOf(resultSet.getInt(1));
            FullName fullName =
                    new FullName(resultSet.getString(2), resultSet.getString(3),resultSet.getString(4));
            Position position = Position.valueOf(resultSet.getString(5));
            LocalDate hired = LocalDate.parse(resultSet.getString(7));
            BigDecimal salary = new BigDecimal(resultSet.getString(8));
            salary = salary.setScale(5, RoundingMode.HALF_UP);
            Employee employee = new Employee(id, fullName, position, hired, salary);

            return (T) employee;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
/*
 public Employee(@JsonProperty("id") final BigInteger id,
                    @JsonProperty("fullName") final FullName fullName,
                    @JsonProperty("position") final Position position,
                    @JsonProperty("hired") final LocalDate hired,
                    @JsonProperty("salary") final BigDecimal salary) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.hired = hired;
        this.salary = salary.setScale(5, RoundingMode.HALF_UP);
    }

 */