Feature: Searching for books
As a user I want to be able to search for available books so I know what I can loan.

Scenario: Getting to the search page
Given the user is on the start page.
When the user navigates to the book search.
Then they can see the search form.

Scenario: Searching for a book
Given the user is on the search page.
When the user performs a book search.
Then they should see a list of matching books.

Scenario: Searching with no matches
Given the user is on the search page.
When the user performs a bad book search.
Then they should see the text "No books found"