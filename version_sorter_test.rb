require_relative 'version_sorter'

version1 = "22.a"
version2 = "1.d"

sorter = VersionSorter.new
newer = sorter.sortVersions(version1, version2)
puts "The " + newer + " version is more recent than the other"
