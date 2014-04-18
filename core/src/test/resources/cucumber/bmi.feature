Feature: BMI calculator

  Scenario Outline: compute BMI
    Given A person
    And the person weights <weight> kilograms
    And the person is <height> meter tall
    When the BMI is computed
    Then the BMI is <result>

  Examples:
    | weight | height | result |
    | 72     | 1.71   | 25     |
    | 60     | 1.8    | 19     |
    | 160    | 1.8    | 49     |