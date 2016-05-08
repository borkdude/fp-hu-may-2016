data Person = Person { firstname :: String
                     , lastname :: String } deriving (Show)

hickey = Person { firstname = "Rich", lastname = "Hickey" }
odersky = Person { firstname = "Martin", lastname = "Odersky" }
wadler = Person { firstname = "Philip", lastname = "Wadler" }

persons = [hickey,odersky,wadler]

mapped = map firstname persons
filtered = filter (\name -> (length name) > 4) mapped
reduced = foldl (\acc name -> acc + length name) 0 filtered 

countTotalLengthOffirstNamesLongerThanFourCharacters persons =
  let firstnames = map firstname persons
      longerThan4 = filter (\name -> (length name) > 4) mapped
      totalLength = foldl (\acc name -> acc + length name) 0 filtered
  in totalLength
  
-- From the command line:
-- $ ghci
-- Prelude> :load haskell-example.hs
     

