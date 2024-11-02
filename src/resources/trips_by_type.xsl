<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/TouristVoucher">
        <GroupedTouristVoucher>
            <xsl:for-each select="Tour">
                <xsl:sort select="Type"/>
                <xsl:if test="not(preceding-sibling::Tour/Type = Type)">
                    <TourGroup>
                        <xsl:attribute name="Type">
                            <xsl:value-of select="Type"/>
                        </xsl:attribute>
                        <xsl:for-each select="/TouristVoucher/Tour[Type=current()/Type]">
                            <xsl:copy-of select="."/>
                        </xsl:for-each>
                    </TourGroup>
                </xsl:if>
            </xsl:for-each>
        </GroupedTouristVoucher>
    </xsl:template>
</xsl:stylesheet>
