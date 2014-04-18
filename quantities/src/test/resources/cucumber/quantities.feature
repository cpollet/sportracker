Feature: General Quantities

  Scenario Outline: unit scales
    Given a <quantity> quantity of <value> <unit>
    When scaled
    Then the resulting quantity is <output_value> in original unit
  Examples:
    | quantity | value    | unit | output_value |
    | length   | 1.555555 | km   | 1.5556       |
    | length   | 1.555555 | m    | 1.5556       |
    | length   | 1.555555 | cm   | 1.5556       |
    | energy   | 1.555555 | kcal | 1.5556       |
    | energy   | 1.555555 | cal  | 1.5556       |
    | energy   | 1.555555 | J    | 1.5556       |
    | duration | 1.555555 | h    | 1.5556       |
    | duration | 1.555555 | min  | 1.5556       |
    | duration | 1.555555 | s    | 1.5556       |
    | speed    | 1.555555 | m/s  | 1.5556       |
    | speed    | 1.555555 | km/h | 1.5556       |
    | mass     | 1.555555 | kg   | 1.5556       |
    | mass     | 1.555555 | g    | 1.5556       |

  Scenario Outline: converting units
    Given a <quantity> quantity of <input_value> <input_unit>
    When the quantity is converted to <output_unit>
    Then the resulting value is <output_value>

  Examples:
    | quantity | input_value | input_unit | output_value | output_unit |
    | length   | 1           | m          | 100          | cm          |
    | length   | 1           | km         | 1000         | m           |
    | length   | 1           | km         | 100000       | cm          |
    | energy   | 1           | kcal       | 4186.8       | J           |
    | energy   | 1           | kcal       | 1000         | cal         |
    | energy   | 1           | cal        | 4.1868       | J           |
    | duration | 1           | min        | 60           | s           |
    | duration | 1           | h          | 3600         | s           |
    | duration | 1           | h          | 60           | min         |
    | speed    | 1           | m/s        | 3.6          | km/h        |
    | speed    | 7.2         | km/h       | 2            | m/s         |
    | mass     | 1           | kg         | 1000         | g           |
    | mass     | 1500        | g          | 1.5          | kg          |

  Scenario Outline: add quantities
    Given a <quantity> quantity of <input_value> <input_unit>
    When adding <added_value> <added_unit>
    Then the resulting quantity is <output_value> in original unit
  Examples:
    | quantity | input_value | input_unit | added_value | added_unit | output_value |
    | length   | 1           | m          | 1           | m          | 2            |
    | length   | 1           | m          | 50          | cm         | 1.5          |

  Scenario Outline: subtracting quantities
    Given a <quantity> quantity of <input_value> <input_unit>
    When subtracting <subtracted_value> <subtracted_unit>
    Then the resulting quantity is <output_value> in original unit
  Examples:
    | quantity | input_value | input_unit | subtracted_value | subtracted_unit | output_value |
    | length   | 1           | m          | 1                | m               | 0            |
    | length   | 1           | m          | 50               | cm              | 0.5          |


