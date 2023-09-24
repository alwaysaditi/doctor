@logintest
Scenario : User tries to login
  Given User enters their login credentials
  When login credentials are successful
  Then user should be able to login