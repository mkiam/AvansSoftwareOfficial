$ ->
  $.get "/persons", (persons) ->
    $.each persons, (index, person) ->
      $("#users").append $("<li>").text person.name