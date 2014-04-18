Feature: Length Quantities
  Scenario Outline: dividing by duration
    Given a <quantity> quantity of <value> <unit>
    When the quantity is divided by a <divisor_quantity> of <divisor_value> <divisor_unit>
    Then the resulting quantity is a <result_quantity> of <result_value> <result_unit>
  Examples:
    | quantity | value | unit | divisor_quantity | divisor_value | divisor_unit | result_quantity | result_value | result_unit |
    | length   | 1     | m    | duration         | 1             | s            | speed           | 1            | m/s         |
    | length   | 20    | km   | duration         | 2             | h            | speed           | 2.7778       | m/s         |
