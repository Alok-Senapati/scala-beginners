package com.learning.oop

object Enums extends App {
  // Enums are datatype(similar to class) consisting of a set of named values/instances
  // Sealed datatype that can not be extended
  enum Permissions {
    case READ, WRITE, EXECUTE, NONE  // Only possible named values/instances
    val someValue = 10
    // Fields/Methods can also be added
    def openDocument: Unit =
      if this == READ then println("Opening Document")
      else println("Read permission is not there. Cannot open document.")
  }

  val permission: Permissions = Permissions.READ
  println(permission)


  // Methods/Fields defined in the enum can also be accessed using the instance and not the enum name
  // Permissions.openDocument // Cause compilation error
  permission.openDocument


  // Enums can also have constructor
  enum PermissionWithBits(bits: Int){
    case READ extends PermissionWithBits(4) // 100
    case WRITE extends PermissionWithBits(2) // 010
    case EXECUTE extends PermissionWithBits(1) // 001
    case NONE extends PermissionWithBits(0) // 000
  }


  // Enums can also have companion objects
  object PermissionWithBits {
    def fromBits(bits: Int): PermissionWithBits = {
      bits match
        case 0 => PermissionWithBits.NONE
        case 1 => PermissionWithBits.EXECUTE
        case 2 => PermissionWithBits.WRITE
        case 4 => PermissionWithBits.READ
        case _ => throw new Exception("Invalid bits passed for getting Permission.")
    }
  }

  val permissionFromBits: PermissionWithBits = PermissionWithBits.fromBits(4)
  println(permissionFromBits)


  // Enum Standart APIs
  // Ordinal -> Returns the index at which the Enum instance is defined inside the enum
  println(permission.ordinal)
  println(Permissions.EXECUTE.ordinal)

  // values method returns an array of all instance values of an enum
  println(PermissionWithBits.values.mkString("Array(", ", ", ")"))

  // valueOf - Enum Instances can be created from a string containing the instance name
  val permissionFromString: PermissionWithBits = PermissionWithBits.valueOf("WRITE")
  println(permissionFromString)

  // println(PermissionWithBits.valueOf("SOMETHING")) // Will throw run time Exception: enum case not found: SOMETHING


}
