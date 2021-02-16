import scala.io.Source

val source = Source.fromFile("../../input/advent-of-code-2020/day4.txt")
val input = source.getLines().mkString("\n")
source.close()

def validateParagraphs(text: String,
                       validator: String => Boolean):
    Vector[Either[String,String]] = {

  def validateParagraphsHelper(text: String,
                               results: Vector[Either[String,String]]):
      Vector[Either[String,String]] = {
    
    // Break off the portion up to the next double empty string.
    val parbreak = text.indexOf("\n\n")
    val (prepar, prerest) = text.splitAt(parbreak)
    // Then trim whitespace on the ends of the paragraph and the rest.
    val (par, rest) = (prepar.trim, prerest.trim)
    if (par.nonEmpty) {
      val par_validated = if (validator(par)) Right(par) else Left(par)
      validateParagraphsHelper(rest, results :+ par_validated)
    } else {
      // Finally, check what's left over.
      val rest_validated = if (validator(rest)) Right(rest) else Left(rest)
      results :+ rest_validated
    }
  }

  // Trim whitespace from the ends of the text before we begin.
  validateParagraphsHelper(text.trim, Vector())
}

def validator(paragraph: String): Boolean = {
  val fields = List("byr:", "iyr:", "eyr:", "hgt:", "hcl:", "ecl:", "pid:")
  fields forall {paragraph contains _}
}

val answer = validateParagraphs(input,validator).count(_.isRight)

println(s"There were $answer valid passports by the given rules.")

import scala.util.matching._

def validator2(paragraph: String): Boolean = {
  // Many of the fields we need to validate are numbers
  // needing to fall within a certain range.
  // This helper handles the extraction of the match and the range check.
  def numValidator(pattern: Regex, group: Int, low: Int, high: Int) = {
    val the_match = pattern.unanchored.findFirstMatchIn(paragraph)
    the_match match {
      case Some(m) => val n = m.group(group).toInt; low <= n && n <= high
      case None => false
    }
  }

  // Fields which require numerical validation.
  val has_birth_year = numValidator("""(^|\s)byr:(\d\d\d\d)($|\s)""".r, 2, 1920, 2002)
  val has_issue_year = numValidator("""(^|\s)iyr:(\d\d\d\d)($|\s)""".r, 2, 2010, 2020)
  val has_expir_year = numValidator("""(^|\s)eyr:(\d\d\d\d)($|\s)""".r, 2, 2020, 2030)
  // (Note: only one of these heights be present.)
  val has_height_in = numValidator("""(^|\s)hgt:(\d\d)in($|\s)""".r, 2, 59, 76)
  val has_height_cm = numValidator("""(^|\s)hgt:(\d\d\d)cm($|\s)""".r, 2, 150, 193)
  
  // Fields which do not require additional validation.
  val has_hair_colour = """(^|\s)hcl:#[0-9a-f]{6}($|\s)""".r.unanchored.matches(paragraph)
  val has_eye_colour = """(^|\s)ecl:(amb|blu|brn|gry|grn|hzl|oth)($|\s)""".r.unanchored.matches(paragraph)
  val has_passport_id = """(^|\s)pid:\d{9}($|\s)""".r.unanchored.matches(paragraph)

  (has_birth_year && has_issue_year && has_expir_year
    && (has_height_in || has_height_cm)
    && has_hair_colour && has_eye_colour && has_passport_id)
}

val answer2 = validateParagraphs(input,validator2).count(_.isRight)

println(s"Under the new rules, $answer2 passports are valid.")
