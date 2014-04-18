Feature: Harris-Benedict daily energy use calculator

  Scenario Outline: compute daily energy use
    Given A <gender> person
    And the person is <years> years old
    And the person weights <weight> kilograms
    And the person is <height> meter tall
    And the person's activity level is <activity>
    When the daily used energy is computed
    Then the daily used energy is <result> kcal

  Examples:
    | gender | years | weight | height | activity | result |
    | male   | 30    | 72     | 1.71   | low      | 2081   |