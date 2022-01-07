# Add any ProGuard configurations specific to this
# extension here.

-keep public class xyz.visdev.ipify.Ipify {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'xyz/visdev/ipify/repack'
-flattenpackagehierarchy
-dontpreverify
