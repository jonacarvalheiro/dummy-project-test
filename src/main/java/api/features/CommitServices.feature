Feature: Commit API

  @CommitStatus
  Scenario: Get commit status
    Given I want to get commit status
    Then The status should be analysed